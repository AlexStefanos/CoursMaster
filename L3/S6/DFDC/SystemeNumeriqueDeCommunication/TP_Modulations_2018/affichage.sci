function affiche_signaux(s,r,r_FA,z,duree,Fe,T)
    t = 0:1/Fe:duree-1/Fe;
    scf(); 
    plot(t,s); 
    plot(t,r,'r');
    plot(t,r_FA,'k');
    plot(t(T*Fe/2:T*Fe:$),z,'ko')
    xlabel("temps (s)")
    legend(["m"; "m estime"; "m apres FA"; "z"])
endfunction


function affiche_z_MDA(z,V0,M)
    scf; plot(z,(0.5-rand(1,length(z),'uniform'))*V0,'.');
    plot([-M/2-1,M/2+1]*V0,[0 0],'k')
    plot([0 0],[-1 1]*V0,'k')
    a = gca();
    a.children(3).children.mark_size = 2;
endfunction


function affiche_z_MAQ(zA,zB,V0,M)
    scf; plot(zA,zB,'.');
    plot([-sqrt(M)/2-1,sqrt(M)/2+1]*V0,[0 0],'k')
    plot([0 0],[-sqrt(M)/2-1,sqrt(M)/2+1]*V0,'k')
    a = gca();
    a.children(3).children.mark_size = 2;
endfunction
