/*
 * tableau.h
 *
 *  Created on: 29 nov. 2020
 *      Author: denli
 */

#ifndef TABLEAU_HEADER
	#define TABLEAU_HEADER
		#ifndef TABLEAU
			#define WHERE_TABLEAU extern
		#else
			#define WHERE_TABLEAU
		#endif
		WHERE_TABLEAU void SaisieNotes(int [],int);
		WHERE_TABLEAU void AfficheNotes(int [],int);
#endif

#ifndef LIBRAIRIE
	#define LIBRAIRIE
		#include <stdio.h>
		#include <stdlib.h>
		#include <string.h>
#endif





