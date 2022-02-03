function affiche_signaux(s,r,r_FA,z,duree,Fe,T)
    t = 0:1/Fe:duree-1/Fe;
    scf(); 
    plot(t,s); 
    plot(t,r,'r--');
    plot(t,r_FA,'k');
    plot(t(round(T*Fe/2):round(T*Fe):$),z,'ko')
    xlabel("temps (s)")
    legend(["s"; "r"; "rFA"; "z"])
endfunction


function affiche_z(z,V0,M)
    scf; 
    plot([-0.2,M-0.5]*V0,[0 0],'w')
    plot(z,(0.5-rand(1,length(z),'uniform'))*V0,'.');
    for seuil=(0.5:1:M-1.5)*V0
        plot([1 1]*seuil,[-1 1]*(M-1)*V0,'r')
    end    
    a = gca();
    a.children($-1).children.mark_size = 2;
endfunction


