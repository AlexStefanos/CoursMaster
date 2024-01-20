
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
    
    input_dim = x_train.shape[1]
    output_dim = 3
    

    #show_one_random_image(x_train.reshape(x_train.shape[0], 28, 28))

    y_train = reshape_classes_to_one_hot_encoding(y_train, output_dim)
    y_test = reshape_classes_to_one_hot_encoding(y_test, output_dim)
    
    #Graphique des loss en fonction du nombre de passes de la descente de gradient
    x_graph = np.arange(0, 1000) #nombre de passes
    y_graph = [] #loss sur le train
    y_test_graph = [] #loss sur le test
    
    #Initialisation de W et b
    W, b = init_params(x_train.shape[1], output_dim)
    goodW,goodB = gradient_descent_one_layer_nn(x_train, y_train, x_test=x_test, y_test=y_test, parameters=(W, b), num_passes=1000, x_graph=x_graph, y_graph=y_graph, y_test_graph=y_test_graph)
    precision_train = 100*np.mean(np.argmax(predict_one_layer_nn(x_train, (goodW, goodB)), axis=1) == np.argmax(y_train, axis=1))
    precision_test = 100*np.mean(np.argmax(predict_one_layer_nn(x_test, (goodW, goodB)), axis=1) == np.argmax(y_test, axis=1))
    print("Precision pour train : ", precision_train)
    print("Precision pour test : ", precision_test)
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


def simple_plot(x, y, other_y=None):
    fig, ax = plt.subplots()
    ax.plot(x, y)

    if(other_y is not None):
        ax.plot(x, other_y, color='green')

    ax.grid()
    plt.show()

#initialisation de W et b aléatoirement avec des petites valeurs
def init_params(input_dim, output_dim):
    W=np.random.randn(input_dim, output_dim)*1e-3
    b=np.random.randn(output_dim)*1e-3
    return W, b

#calcul de Z
def predict_one_layer_nn(X, parameters):
    W, b = parameters
    Z=np.dot(X, W) + b
    Y=stable_softmax(Z)
    return Y

#fonction softmax
def softmax(Z):
    return np.exp(Z)/np.sum(np.exp(Z),axis=1,keepdims=True)

def stable_softmax(Z):
    stable_Z = Z - np.max(Z,axis=1,keepdims=True)
    return np.exp(stable_Z)/np.sum(np.exp(stable_Z),axis=1,keepdims=True)
    
def evaluate_loss(y, y_hat) :
    return -(1/y.shape[0])*np.sum(y*np.log(y_hat+1e-16)+(1-y)*np.log((1-y_hat)+1e-16))

def partial_derivative_one_layer_nn(x,y,y_hat):
    #calcul de dw et db
    dw = np.dot(x.T,(y_hat-y))
    db = np.sum(y_hat-y,axis=0)
    return dw, db

def gradient_descent_one_layer_nn(x_train, y_train, x_test=None, y_test=None, parameters=None, num_passes=200, epsilon=1e-3, x_graph=None, y_graph=None, y_test_graph=None):
    W, b = parameters
    for i in range(num_passes):
        print(i)
        y_hat = predict_one_layer_nn(x_train, (W, b))
        #ajouter les loss dans les listes pour le graphique
        if(y_graph is not None and y_test_graph is not None):
            y_graph.append(evaluate_loss(y_train, y_hat))
            y_test_graph.append(evaluate_loss(y_test, predict_one_layer_nn(x_test, (W, b))))
        dw, db = partial_derivative_one_layer_nn(x_train, y_train, y_hat)
        W=W-epsilon*dw
        b=b-epsilon*db
        

    return W,b
        
if __name__ == "__main__":
    main()