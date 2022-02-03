function y = hex2bin(x)

// x = chaine hexa
// y = tableau de dim 1,4*longueur(x)

y = zeros(1,4*length(x));

index = 1:4;
for i=1:length(x)
  c = part(x,i);
  select c 
    case '0' then c_bin = [0 0 0 0];
    case '1' then c_bin = [0 0 0 1];
    case '2' then c_bin = [0 0 1 0];
    case '3' then c_bin = [0 0 1 1];
    case '4' then c_bin = [0 1 0 0];
    case '5' then c_bin = [0 1 0 1];
    case '6' then c_bin = [0 1 1 0];
    case '7' then c_bin = [0 1 1 1];
    case '8' then c_bin = [1 0 0 0];
    case '9' then c_bin = [1 0 0 1];
    case 'A' then c_bin = [1 0 1 0];
    case 'B' then c_bin = [1 0 1 1];
    case 'C' then c_bin = [1 1 0 0];
    case 'D' then c_bin = [1 1 0 1];
    case 'E' then c_bin = [1 1 1 0];
    case 'F' then c_bin = [1 1 1 1];
    end
  y(index) = c_bin;
  index = index + 4;
end

endfunction
