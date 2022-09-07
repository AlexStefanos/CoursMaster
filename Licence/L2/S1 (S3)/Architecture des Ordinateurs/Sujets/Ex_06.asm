				org		$4

Vector_001		dc.l	Main

				org		$5000

Main			move.l	#$12345678,d7

next1			moveq.l	#1,d1
				cmpi.b	#$80,d7
				blt		next2
				moveq.l	#2,d1

next2			move.l	d7,d2
				swap	d2
				ror.b	#4,d2
				rol.l	#8,d2
				rol.w	#4,d2
				
next3			clr.l	d3
				move.l	d7,d0
loop3			addq.l	#1,d3
				subq.w	#1,d0
				bne		loop3
				
next4			clr.l	d4
				move.l	d7,d0
loop4			addq.l	#1,d4
				dbra	d0,loop4

quit			illegal