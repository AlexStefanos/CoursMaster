import turtle

# Code de configuration de la fenetre
picture_width  = 800
picture_height = 600
turtle.setup (width=picture_width, height=picture_height)
# Fin de code de configuration de la fenetre

# Ici commence le code pour dessiner

turtle.right(90)
turtle.forward(100)
turtle.left(90)
turtle.forward(100)


# Code d'attente pour fermer la fenetre
turtle.hideturtle ()
turtle.exitonclick ()