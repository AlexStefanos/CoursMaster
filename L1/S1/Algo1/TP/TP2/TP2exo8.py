ex1=int(input("Donnez la note du 1er examen : "))
ex2=int(input("Donnez la note du 2e examen : "))
ex3=int(input("Donnez la note du 3e examen : "))
if ((ex1>=9) and (ex2>=9) and (ex3>=9)) or (((ex1+ex2+ex3)/3>=10) and ((ex1>=8))):
    print("Vous êtes admis.")
else :
    print ("Vous n'êtes pas admis.")
    
