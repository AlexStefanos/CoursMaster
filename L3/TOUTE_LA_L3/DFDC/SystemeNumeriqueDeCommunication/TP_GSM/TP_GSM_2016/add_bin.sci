function c_err = add_bin(c,pos)

err = zeros(1,4); err(pos) = 1;

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

c_err_bin = modulo(c_bin + err ,2);

c_err_dec = 8*c_err_bin(1) + 4*c_err_bin(2) + 2*c_err_bin(3) + c_err_bin(4);
c_err = dec2hex(c_err_dec);

endfunction

