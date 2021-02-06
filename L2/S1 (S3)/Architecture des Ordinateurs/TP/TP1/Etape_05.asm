				org		$4

Vector_001		dc.l	Main

				org		$500

Main 			; Addition sur 8 bits.
 				move.b #$b4,d0
 				move.b #$4c,d1
 				add.b d0,d1
				; Addition sur 16 bits.
				move.w #$b4,d0
 				move.w #$4c,d1
 				add.w d0,d1
 				; Addition sur 16 bits.
 				move.w #$4ac9,d0
 				move.w #$d841,d1
 				add.w d0,d1
 				; Addition sur 32 bits.
 				move.l #$ffffffff,d0
 				move.l #$15,d1
 				add.l	d0,d1