import turtle

# Code de configuration de la fenetre
picture_width  = 800
picture_height = 600
turtle.setup (width=picture_width, height=picture_height)
# Fin de code de configuration de la fenetre

# Ici commence le code pour dessiner

# On deplace la tortue sur le bord droit de la fenetre
turtle.up()
turtle.goto(-400,0)
turtle.down()

for i in range(0,2,1) :
    turtle.forward(40)
    turtle.left(90)
    turtle.forward(40)
    turtle.right(90)
    turtle.forward(40)
    turtle.right(90)
    turtle.forward(40)
    turtle.left(90)


# Code d'attente pour fermer la fenetre
turtle.hideturtle ()
turtle.exitonclick ()
