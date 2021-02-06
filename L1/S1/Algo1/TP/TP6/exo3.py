import turtle

# Code de configuration de la fenetre
picture_width = 800
picture_height = 600
turtle.setup (width=picture_width, height=picture_height)
# Fin de code de configuration de la fenetre

# Ici commence le code pour dessiner

#Dessin du carré
turtle.right(90)
turtle.forward(100)
turtle.left(90)
turtle.forward(100)
turtle.left(90)
turtle.forward(100)
turtle.left(90)
turtle.forward(100)
#Triangle au-dessus du carré
turtle.right(135)
turtle.forward(70)
turtle.right(90)
turtle.forward(70)
#Question4
turtle.up()
turtle.goto(150,150)
turtle.down()

# Code d’attente pour fermer la fenetre
turtle.hideturtle ()
turtle.exitonclick ()
