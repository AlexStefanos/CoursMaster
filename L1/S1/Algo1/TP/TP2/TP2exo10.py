places = int(input("Donnez un nombres de places : "))
prix = 0
if places >= 10:
    prix = (places - 10) * 5.5 + 6 * 5 + 8 * 5
elif places > 5 and places <= 10:
    prix = (places - 5) * 6 + 8 * 5
elif places > 0 and places <= 5:
    prix = places * 8
print("Pour", places, "places, vous devez", prix, "euros")
