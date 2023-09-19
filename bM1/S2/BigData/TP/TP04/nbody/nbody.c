#include <stdio.h>
#include <stdlib.h>

#include <time.h>
#include <limits.h>
#include <math.h>

#include <x86intrin.h> // gcc specific

#include <cpuid.h>

// number of particles
#define N 8*256
// interval of time (s)
#define DT 1
// const to avoid division by 0
#define EPS 1e-6f
// gravitationnal force (not used)
#define G 6.673e-11
//% of ERROR accepted 
#define ERROR 0.5f

/*************************************************************************
                           timing utilities
**************************************************************************/

static long time1, time_diff, sum_diff= 0, max=0;

static inline void reset_stats(){
    sum_diff= 0;
    max=0;
}

static inline void start_timer(){
    time1= __rdtsc();
}

static inline void pause_timer(){
    time_diff= __rdtsc()-time1;
    sum_diff += time_diff;
    if (time_diff > max) max= time_diff;
}

static inline void display_stats(char *msg,int nb_loops){
    static long old_avg;
    long avg= (float) (sum_diff-max)/(nb_loops-1);
    printf("%s: average= %12li\n",msg,avg);
    if (old_avg == 0){ // first call
        old_avg= avg;
    }else{ // second call
        float avg_speedup= (float) old_avg/avg;
        printf("Speedup: average= %5.2f\n",avg_speedup);
        old_avg= 0;
    };
}

/*************************************************************************
                           scalar function
**************************************************************************/

// initialize position, velocity, force and mass of particules
void init_particules(float px[], float py[], float pz[], 
                     float m[]){
 
    for (int p=0; p<N; p++){
        m[p]= drand48()*1000;
        px[p]= 100 - drand48()*200;
        py[p]= 100 - drand48()*200;
        pz[p]= 100 - drand48()*200;
    }
}

// save positions of particule p[] into s[]
void save_positions(float px[], float py[], float pz[], 
                     float sx[], float sy[], float sz[]){
    
    for (int p=0; p<N; p++){
        sx[p]= px[p];
        sy[p]= py[p];
        sz[p]= pz[p];
    }
}

// restore positions from saved position and reset velocity
void restore_positions(float px[], float py[], float pz[], 
                       float vx[], float vy[], float vz[],
                       float rx[], float ry[], float rz[]){
 
    for (int p=0; p<N; p++){
        px[p]= rx[p];
        py[p]= ry[p];
        pz[p]= rz[p];
        vx[p]= vy[p]= vz[p]= 0;
    }
}

// compare particule positions 
int compare_positions(float px[], float py[], float pz[], 
                      float sx[], float sy[], float sz[]){
    
    for (int p=0; p<N; p++){
        float diff_x= fabs(sx[p] - px[p]);
        if (diff_x > ERROR*fabs(sx[p])){ // marge d'erreur depassee
            printf("px[%i]=%f and sx[%i]=%f differs by %f\n",p,px[p],p,sx[p],diff_x);
            return 1;
        }
        float diff_y= fabs(sy[p] - py[p]);
        if (diff_y > ERROR*fabs(sy[p])){ 
            printf("py[%i]=%f and sy[%i]=%f differs by %f\n",p,py[p],p,sy[p],diff_y);
            return 1;
        }
        float diff_z= fabs(sz[p] - pz[p]);
        if (diff_z > ERROR*fabs(sz[p])){ 
            printf("pz[%i]=%f and sz[%i]=%f differs by %f\n",p,pz[p],p,sz[p],diff_z);
            return 1;
        }        
    }
    return 0;
}

#pragma GCC push_options
#pragma GCC target ("no-avx")

// simulate one step of gravitational interactions between N particules
void scal_simulation(float px[], float py[], float pz[], 
                     float vx[], float vy[], float vz[],
                     float m[]){
    
    float fx[N], fy[N], fz[N]; // cumulative force by others particules 
    for (int p1=0; p1<N; p1++){
        fx[p1]= 0;
        fy[p1]= 0;
        fz[p1]= 0;
        for (int p2= 0; p2<N; p2++){
            // add force to particle p1 by particle p2
            //if (p1 != p2){
                float diff_x= px[p2] - px[p1];
                float diff_y= py[p2] - py[p1];
                float diff_z= pz[p2] - pz[p1];
                float dist2= EPS + diff_x * diff_x + diff_y * diff_y + diff_z * diff_z;
                float dist25= dist2 * sqrtf(dist2);
                float F= m[p1]*m[p2] / dist25; // real formula: x G
                fx[p1] += F * diff_x ;
                fy[p1] += F * diff_y ;
                fz[p1] += F * diff_z ;
            //}
        }
    }
    for (int p=0; p<N; p++){// update velocity and position of particle p at t+dt
        vx[p] += fx[p] * DT / m[p];
        vy[p] += fy[p] * DT / m[p];
        vz[p] += fz[p] * DT / m[p];
        px[p] += vx[p] * DT;
        py[p] += vy[p] * DT;
        pz[p] += vz[p] * DT;
    }
}

#pragma GCC pop_options    

/*************************************************************************
                           intrinsics function
**************************************************************************/

// print 8 floats contained in a 256 bit register
void printf_m256(char *msg, __m256 var){
    
    float *val = (float *) &var;
    printf("%s ",msg);
    for (int i=0; i<8; i++){
        printf("%f ",val[i]);
    }
    printf("\n");
}

// print 8 integers contained in a 256 bit register
void printf_m256i(char *msg, __m256i var){
    
    int *val = (int *) &var;
    printf("%s ",msg);
    for (int i=0; i<8; i++){
        printf("%d ",val[i]);
    }
    printf("\n");
}

// simulate one step of gravitational interactions between N particules
void simd_simulation(float px[], float py[], float pz[], 
                     float vx[], float vy[], float vz[],
                     float m[]){

    float fx[N] __attribute__((aligned(32)));
    float fy[N] __attribute__((aligned(32)));
    float fz[N] __attribute__((aligned(32)));
    for(int p1 = 0; p1 < N; p1 += 8) {
        __m256 p1x_8 = _mm256_load_ps(&px[p1]);
        __m256 p1y_8 = _mm256_load_ps(&py[p1]);
        __m256 p1z_8 = _mm256_load_ps(&pz[p1]);
        __m256 m1_8 = _mm256_load_ps(&m[p1]);
        __m256 f1x_8 = _mm256_set1_ps(0); //fx[p1] = 0;
        __m256 f1y_8 = _mm256_set1_ps(0);
        __m256 f1z_8 = _mm256_set1_ps(0);
        for(int p2 = 0; p2 < N; p2++) {
            __m256 diff_x_8 = _mm256_set1_ps(px[p2]);
            __m256 diff_y_8 = _mm256_set1_ps(py[p2]);
            __m256 diff_z_8 = _mm256_set1_ps(pz[p2]);
            diff_x_8 = _mm256_sub_ps(diff_x_8, p1x_8);
            diff_y_8 = _mm256_sub_ps(diff_y_8, p1y_8);
            diff_z_8 = _mm256_sub_ps(diff_z_8, p1z_8);
            __m256 dist2_8 = _mm256_set1_ps(EPS);
            dist2_8 = _mm256_fmadd_ps(diff_x_8, diff_x_8, dist2_8);
            dist2_8 = _mm256_fmadd_ps(diff_y_8, diff_y_8, dist2_8);
            dist2_8 = _mm256_fmadd_ps(diff_z_8, diff_z_8, dist2_8);
            __m256 sqrtdist2_8 = _mm256_sqrt_ps(dist2_8);
            __m256 dist25_8 = _mm256_mul_ps(dist2_8, sqrtdist2_8);
            __m256 m2_8 = _mm256_set1_ps(m[p2]);
            __m256 F_8 = _mm256_mul_ps(m1_8, m2_8);
            F_8 = _mm256_div_ps(F_8, dist25_8);
            f1x_8 = _mm256_fmadd_ps(F_8, diff_x_8, f1x_8);
            f1y_8 = _mm256_fmadd_ps(F_8, diff_y_8, f1y_8);
            f1z_8 = _mm256_fmadd_ps(F_8, diff_z_8, f1z_8);
        }
        _mm256_store_ps(&fx[p1], f1x_8);
        _mm256_store_ps(&fy[p1], f1y_8);
        _mm256_store_ps(&fz[p1], f1z_8);
    }
}

/*************************************************************************
                           main function
**************************************************************************/

// mass of particules
float mass[N];
// initial positions of particules
float init_x[N], init_y[N], init_z[N];
// current positions of particules
float position_x[N];
float position_y[N];
float position_z[N];
// saved positions of particules (after scalar simulation)
float save_x[N], save_y[N], save_z[N];
// current velocity of particules
float velocity_x[N];
float velocity_y[N];
float velocity_z[N];

int main(int argc,char *argv[]){
    
    if (argc<2){
        printf("error: missing loop number on command line, usage ./nbody <n>\n");
        return -1;
    }
    const int nb_steps= atoi(argv[1]);
    
    srand48(time (0));
    init_particules(init_x,init_y,init_z,mass);   
    restore_positions(position_x,position_y,position_z,
                      velocity_x,velocity_y,velocity_z,
                      init_x,init_y,init_z);
    for(int i=0; i<nb_steps; i++){
        start_timer();
        scal_simulation(position_x,position_y,position_z,
                        velocity_x,velocity_y,velocity_z,
                        mass);
        pause_timer();
    }
    display_stats("Scal stats",nb_steps);
    reset_stats();
    save_positions(position_x,position_y,position_z,
                   save_x,save_y,save_z);
    restore_positions(position_x,position_y,position_z,
                      velocity_x,velocity_y,velocity_z,
                      init_x,init_y,init_z);
    for(int i=0; i<nb_steps; i++){
        start_timer();
        simd_simulation(position_x,position_y,position_z,
                        velocity_x,velocity_y,velocity_z,
                        mass);
        pause_timer();
    }
    display_stats("SIMD stats",nb_steps);
    if (compare_positions(position_x,position_y,position_z,
                          save_x,save_y,save_z)){
        printf("*** error ****\n");
    }else{
        printf("OK\n");
    }
}
