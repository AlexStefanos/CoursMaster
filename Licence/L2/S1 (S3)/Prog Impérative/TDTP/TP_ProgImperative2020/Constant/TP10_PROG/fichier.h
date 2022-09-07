/*
 * fichier.h
 *
 *  Created on: 29 nov. 2020
 *      Author: denli
 */

#ifndef FICHIER_HEADER
	#define FICHIER_HEADER
		#ifndef FICHIER
			#define WHERE_FICHIER extern
		#else
			#define WHERE_FICHIER
		#endif
		WHERE_FICHIER void SauvegFichTxt(char*);
		WHERE_FICHIER void LectFichTxt(char*);
		WHERE_FICHIER void SauvegFichBin(char*,char*);
		WHERE_FICHIER void LectFichBin(char*,char*);
#endif

#ifndef LIBRAIRIE
	#define LIBRAIRIE
		#include <stdio.h>
		#include <stdlib.h>
		#include <string.h>
#endif
