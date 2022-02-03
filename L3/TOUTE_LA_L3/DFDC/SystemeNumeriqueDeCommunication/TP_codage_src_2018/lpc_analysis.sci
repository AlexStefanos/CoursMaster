// Analyse LPC (levinson)
function [a, sigma2] = lpc_analysis(x, p)

N = length(x);

// Calcul rx (!! rx(1) correspond à l'autocor en 0 !)  
//  rx = zeros(p+1,1);
//  for k=1:p+1
//    // rx(k) = mean(x(1:N-k+1).*x(k:N)); // autocor non biaisée ne marche pas
//    rx(k) = sum(x(1:N-k+1).*x(k:N))/N;   
//  end
rx = corr(x,p+1)';

// init
  a=zeros(1,p);
  sigma2 = 0;
  alpha = rx(1);
  
// algo
  for q=0:p-1
    a_old = a;
    a(q+1) = -( rx(q+2) + a_old(1:q)*rx(q+1:-1:2) )/alpha;
    for i=1:q
      a(i) = a_old(i) + a(q+1)*a_old(q+1-i);
    end
    alpha = alpha * (1-a(q+1)^2);
  end
  
sigma2 = alpha;




endfunction

