
import random
import time
import torch
import numpy as np
from sklearn import datasets
from sklearn.model_selection import train_test_split
from matplotlib import pyplot as plt
from matplotlib import image as mpimg

import warnings
warnings.filterwarnings("error")

dtype = torch.float32 #type de données
device = "cpu" if torch.cuda.is_available() else "cpu"
torch.set_default_device(device)

#clement reverdy
#PARTIE 2 : Implémentation d'un réseau de neurones à deux couches
def main():
    print("Saluton mondo!")

    fp_train_x = './data/x.npy'
    fp_test_x = './data/y.npy'
    fp_train_y = './data/xt.npy'
    fp_test_y = './data/yt.npy'

    x_train, y_train, x_test, y_test = load_data(
        fp_train_x,
        fp_test_x,
        fp_train_y,
        fp_test_y,
        only_1_7_8=True)
    
    input_dim = x_train.shape[1] #shape[0] = nombre d'images = N = 1000, shape[1] = nombre de pixels par image = D = 784 (28*28)
    output_dim = 3 #nombre de classes en sortie = 3 (1,7,8)
    Q=10 #dimension de la couche cachée
      
    y_train = reshape_classes_to_one_hot_encoding(y_train, output_dim)
    y_test = reshape_classes_to_one_hot_encoding(y_test, output_dim) 
    
    #on transfomr tout en tensor torch
    x_train = torch.tensor(x_train, dtype=dtype)
    y_train = torch.tensor(y_train, dtype=dtype)
    x_test = torch.tensor(x_test, dtype=dtype)
    y_test = torch.tensor(y_test, dtype=dtype)
    """
    W1, b1, W2, b2 = init_randoms_params(input_dim, Q, output_dim)
    y_graph = [] #loss sur le train
    y_test_graph = [] #loss sur le test
    num_passes= 10_000
    W1,b1,W2,b2 = gradient_descent_torch(x_train=x_train, y_train=y_train, x_test=x_test, y_test=y_test,parameters=(W1,b1,W2,b2),model=None, hidden_dim=100, num_passes=num_passes, epsilon=1e-4,y_graph=y_graph, y_test_graph=y_test_graph) #avec descente de gradient
    y_hat_train=predict_two_layer_nn(x_train, (W1, b1, W2, b2))
    y_hat_test=predict_two_layer_nn(x_test, (W1, b1, W2, b2))
    
    #rajout des .detach().numpy() pour convertir les tensors torch en arrays numpy
    print("Precision pour train : ", 100*np.mean(np.argmax(y_hat_train.detach().numpy(), axis=1) == np.argmax(y_train.detach().numpy(), axis=1)))
    print("Precision pour test : ", 100*np.mean(np.argmax(y_hat_test.detach().numpy(), axis=1) == np.argmax(y_test.detach().numpy(), axis=1)))
    
    #Affichage du graphique
    simple_plot(np.arange(0, num_passes), y_graph, y_test_graph)
    """
    
    #PARTIE 3 : Implémentation d'un réseau de neurones à deux couches avec PyTorch
    #on utilise la classe MLP définie plus bas
    y_graph = [] #loss sur le train
    y_test_graph = [] #loss sur le test
    model = MLP()

    optimizer = torch.optim.SGD(model.parameters(), lr=1e-4, momentum=0.9)
    for i in range(10_000):
        y_hat = model(x_train)
        loss = evaluate_loss_torch(y_train, y_hat)
        loss.backward()
        optimizer.step()
        optimizer.zero_grad()
        y_graph.append(loss.item())
        y_test_graph.append(evaluate_loss_torch(y_test, model(x_test)).data.numpy())
        if(i%100==0):
            print("Loss à l'itération ",i," : ",evaluate_loss_torch(y_train, y_hat)) 
    print("Precision pour train : ", 100*np.mean(np.argmax(y_hat.detach().numpy(), axis=1) == np.argmax(y_train.detach().numpy(), axis=1)))
    print("Precision pour test : ", 100*np.mean(np.argmax(model(x_test).detach().numpy(), axis=1) == np.argmax(y_test.detach().numpy(), axis=1)))       
"""
def gradient_descent_torch(x_train, y_train, x_test, y_test, parameters, model=None, hidden_dim=100, num_passes=200, epsilon=1e-4,y_graph=None, y_test_graph=None):
    W1, b1, W2, b2 = parameters  
    print("Paramètres : ",parameters)
    optimizer=torch.optim.SGD(params=parameters, lr=epsilon, momentum=0.9)      
    for i in range(num_passes):
        #on utilise le modèle        
        y_hat=predict_two_layer_nn(x_train, (W1, b1, W2, b2))
        loss=evaluate_loss_torch(y_train, y_hat)

        loss.backward() #calcul des gradients
        optimizer.step() #mise à jour des paramètres
        optimizer.zero_grad() #réinitialisation des gradients
        

        y_graph.append(loss.item())
        y_test_graph.append(evaluate_loss_torch(y_test, predict_two_layer_nn(x_test, (W1, b1, W2, b2))).data.numpy())
        if(i%100==0):
            print("Loss à l'itération ",i," : ",evaluate_loss_torch(y_train, y_hat))
    return W1, b1, W2, b2
    """
def load_data(
        filepath_data_x_train,
        filepath_data_y_train,
        filepath_data_x_test,
        filepath_data_y_test,
        only_1_7_8=False):
    
    x_train = np.load(filepath_data_x_train)
    y_train = np.load(filepath_data_y_train)
    x_test = np.load(filepath_data_x_test)
    y_test = np.load(filepath_data_y_test)

    if (only_1_7_8):
        y_train = np.array(
            [
                0 if (y_train[i] == 1) else (1 if (y_train[i] == 7) else 2)
                for i in range(len(y_train))]
        )
        y_test = np.array(
            [
                0 if (y_test[i] == 1) else (1 if (y_test[i] == 7) else 2)
                for i in range(len(y_test))]
        )

    return x_train, y_train, x_test, y_test


def reshape_classes_to_one_hot_encoding(y, output_dim):
    y = np.array([[float(y[i] == j) for j in range(output_dim)]
                 for i in range(y.shape[0])])
    return y

def init_randoms_params(input_dim=784, hidden_dim=10, output_dim=3):
    W1 = np.random.randn(input_dim,hidden_dim)*1e-3 #W1 est de dimension DxQ (784x10)
    b1=np.random.randn(1,hidden_dim)*1e-3 #b1 est de dimension Q (10)
    W2 = np.random.randn(hidden_dim, output_dim)*1e-3 #W2 est de dimension Qx3
    b2 = np.random.randn(1,output_dim)*1e-3 #b2 est de dimension 3
    
    #Puis on convertit les arrays numpy en tensors torch
    W1 = torch.tensor(W1,dtype=dtype ,requires_grad=True)
    b1 = torch.tensor(b1,dtype=dtype, requires_grad=True)
    W2 = torch.tensor(W2, dtype=dtype, requires_grad=True)
    b2 = torch.tensor(b2, dtype=dtype, requires_grad=True)   
    return W1, b1, W2, b2

def predict_two_layer_nn(x, params):
    W1, b1, W2, b2 = params
    Z1 = torch.matmul(x, W1) + b1
    a1 = torch.relu(Z1)
    Z2 = torch.matmul(a1, W2) + b2
    yhat = torch.softmax(Z2,1)
    return yhat    

#y et yhat sont des tensors torch
def evaluate_loss_torch(y, y_hat):
    return -(1/y.shape[0])*torch.sum(y*torch.log(y_hat+1e-16)+(1-y)*torch.log((1-y_hat)+1e-16))

def show_one_random_image(x):
    plt.title("azerty")
    plt.imshow(x[random.randint(0, len(x)-1)])
    plt.show()

def simple_plot(x, y, other_y=None):
    fig, ax = plt.subplots()
    ax.plot(x, y)
    if(other_y is not None):
        ax.plot(x, other_y, color='green')

    ax.grid()
    plt.show()

class MLP(torch.nn.Module):
    def __init__(self, input_dim=784, hidden_dim=100, output_dim=3):
        super(MLP, self).__init__()

        self.c1 = torch.nn.Linear(input_dim, hidden_dim)
        self.c1.weight.data.normal_(0, 1e-3) #initialisation des poids aléatoirement selon une loi normale de moyenne 0 et d'écart-type 1e-3    
        self.c1.bias.data.normal_(0, 1e-3)
        self.c2 = torch.nn.Linear(hidden_dim, output_dim)
        self.c2.weight.data.normal_(0, 1e-3)
        self.c2.bias.data.normal_(0, 1e-3)
    
    def forward(self, x):
        x = self.c1(x)
        x = torch.relu(x)
        x = self.c2(x)
        x = torch.softmax(x,1)
        return x

if __name__ == "__main__":
    main()