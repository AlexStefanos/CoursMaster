
import sys
import random
import time
import numpy as np
from sklearn import datasets
from sklearn.model_selection import train_test_split
from matplotlib import pyplot as plt
from matplotlib import image as mpimg

import warnings
warnings.filterwarnings("error")

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
    
    #la couche cachée est de dimension NxQ?

    #show_one_random_image(x_train.reshape(x_train.shape[0], 28, 28))
    Q=10 #dimension de la couche cachée
    y_train = reshape_classes_to_one_hot_encoding(y_train, output_dim)
    y_test = reshape_classes_to_one_hot_encoding(y_test, output_dim) 
    
    W1, b1, W2, b2 = init_randoms_params(input_dim, Q, output_dim)    #initialisation de W1, b1, W2 et b2 avec une dimension de Q
    yhat = predict_two_layer_nn(x_train, (W1, b1, W2, b2))
    print("Loss sans descente de gradient : ", evaluate_loss(y_train, yhat))
   
    #Graphique des loss en fonction du nombre de passes de la descente de gradient
    num_passes= 2000
    x_graph = np.arange(0, num_passes) #nombre de passes
    y_graph = [] #loss sur le train
    y_test_graph = [] #loss sur le test
    W1, b1, W2, b2 = gradient_descent_two_layers_nn(x_train, y_train, x_test, y_test, num_passes=num_passes,x_graph=x_graph, y_graph=y_graph, y_test_graph=y_test_graph)
    
    y_train_hat1 = predict_two_layer_nn(x_train, (W1, b1, W2, b2))
    y_test_hat = predict_two_layer_nn(x_test, (W1, b1, W2, b2))
    print("Precision pour : ", 100*np.mean(np.argmax(y_train_hat1, axis=1) == np.argmax(y_train, axis=1)))
    print("Precision : ", 100*np.mean(np.argmax(y_test_hat, axis=1) == np.argmax(y_test, axis=1)))

    #Affichage du graphique
    plt.figure()
    plt.plot(x_graph, y_graph, label='Train loss')
    plt.plot(x_graph, y_test_graph, label='Test loss')
    plt.legend()
    plt.show()
    
            
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


def show_one_random_image(x):
    plt.title("azerty")
    plt.imshow(x[random.randint(0, len(x)-1)])
    plt.show()


def init_randoms_params(input_dim=784, hidden_dim=10, output_dim=3):
    W1 = np.random.randn(input_dim,hidden_dim)*1e-2 #W1 est de dimension DxQ (784x10)
    b1=np.random.randn(hidden_dim,)*1e-2 #b1 est de dimension Q (10)
    W2 = np.random.randn(hidden_dim, output_dim)*1e-2 #W2 est de dimension Qx3
    b2 = np.random.randn(output_dim,)*1e-2 #b2 est de dimension 3   
    return W1, b1, W2, b2

def evaluate_loss(y, y_hat) :
    return -(1/y.shape[0])*np.sum(y*np.log(y_hat+1e-16)+(1-y)*np.log((1-y_hat)+1e-16))

def dtanh(Z):
    return 1 - pow(np.tanh(Z),2) #dérivée de tanh pour la rétropropagation

#fonction softmax
def softmax(Z): #Z est de dimension NxQ
    return np.exp(Z)/np.sum(np.exp(Z),axis=1,keepdims=True)

def stable_softmax(Z): #Z sans l'exponentiel gigantesque
    stable_Z = Z - np.max(Z,axis=1,keepdims=True)
    return np.exp(stable_Z)/np.sum(np.exp(stable_Z),axis=1,keepdims=True)


def predict_two_layer_nn(X, parameters):
    W1, b1, W2, b2 = parameters
    Z1 = np.dot(X, W1) + b1 #Z1 est de dimension NxQ
    a1 = np.tanh(Z1) #a1 est de dimension NxQ
    Z2 = np.dot(a1, W2) + b2 #Z2 est de dimension Nx3
    return stable_softmax(Z2) #Yhat est de dimension Nx3


def partial_derivative_two_layers_nn(x, y, y_hat, parameters):
    W1, b1, W2, b2 = parameters
    
    delta3 = y_hat - y 
    z1 = np.dot(x, W1) + b1
    a1 = np.tanh(z1)
    da1 = dtanh(z1) #dérivée de a1
    delta2=np.multiply(da1,np.dot(delta3,W2.T))
    dw1 = np.dot(x.T,delta2)
    db1 = np.sum(delta2,axis=0)
    dw2 = np.dot(a1.T,delta3)
    db2 = np.sum(delta3,axis=0)

    return dw1, db1, dw2, db2

def gradient_descent_two_layers_nn(x_train, y_train, x_test, y_test, hidden_dim=10, num_passes=5000, epsilon=1e-5, x_graph=None, y_graph=None, y_test_graph=None):
    W1, b1, W2, b2 = init_randoms_params(input_dim=x_train.shape[1], hidden_dim=hidden_dim, output_dim=y_train.shape[1])
    for i in range(num_passes):
        y_hat = predict_two_layer_nn(x_train, (W1, b1, W2, b2))
        dw1, db1, dw2, db2 = partial_derivative_two_layers_nn(x_train, y_train, y_hat, (W1, b1, W2, b2))
        
        W1 = W1 - epsilon*dw1
        b1 = b1 - epsilon*db1
        W2 = W2 - epsilon*dw2
        b2 = b2 - epsilon*db2
        
        y_graph.append(evaluate_loss(y_train, y_hat))
        y_test_graph.append(evaluate_loss(y_test, predict_two_layer_nn(x_test, (W1, b1, W2, b2))))
        if(i%100==0):
            print("Loss à l'itération ",i," : ",evaluate_loss(y_train, y_hat))
    return W1, b1, W2, b2

if __name__ == "__main__":
    main() 