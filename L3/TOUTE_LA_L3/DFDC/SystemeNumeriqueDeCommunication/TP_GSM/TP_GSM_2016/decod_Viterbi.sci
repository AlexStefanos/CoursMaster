function x_dec = decod_Viterbi(y)

// decodeur correspondant à C = [[1 1 1];[1 0 1]];

exec("viterbi.sci");

motif1(:,:,1) = [[1 3];
                 [0 0];
		 [0 0];
		 [0 0]];
	     
motif1(:,:,2) = [[0 1];
                 [0 0];
		 [0 0];
		 [0 0]];

motif1(:,:,3) = [[0 1];
                 [0 0];
		 [0 0];
		 [0 0]];

motif2(:,:,1) = [[1 3];
                 [0 0];
		 [2 4];
		 [0 0]];
	     
motif2(:,:,2) = [[0 1];
                 [0 0];
		 [1 0];
		 [0 0]];

motif2(:,:,3) = [[0 1];
                 [0 0];
		 [0 1];
		 [0 0]];

	 
motifNm1(:,:,1) = [[1 0];
                   [1 0];
   	 	   [2 0];
 		   [2 0]];
	     
motifNm1(:,:,2) = [[0 0];
                   [1 0];
		   [1 0];
		   [0 0]];

motifNm1(:,:,3) = [[0 0];
                   [1 0];
		   [0 0];
		   [1 0]];

motifN(:,:,1) = [[1 0];
                 [1 0];
		 [0 0];
		 [0 0]];
	     
motifN(:,:,2) = [[0 0];
                 [1 0];
		 [0 0];
		 [0 0]];

motifN(:,:,3) = [[0 0];
                 [1 0];
		 [0 0];
		 [0 0]];

	
motifn(:,:,1) = [[1 3];
                 [1 3];
		 [2 4];
		 [2 4]];
	     
motifn(:,:,2) = [[0 1];
                 [1 0];
		 [1 0];
		 [0 1]];

motifn(:,:,3) = [[0 1];
                 [1 0];
		 [0 1];
		 [1 0]];

// Mise de y sous forme de matrice 2*N/2
N = length(y);
ybis = zeros(2,N/2);
ybis(1,:) = y(1:2:N);
ybis(2,:) = y(2:2:N);

// Decodage Viterbi
x_dec = viterbi(ybis,motif1,motif2,motifn,motifNm1,motifN);

endfunction