
import socket

#Definimos la ip de la entrada de mensajes
localIP = "127.0.0.1"

#Escogemos el puerto entre los disponibles 
localPort = 20001

#Definimos el tamano del buffer
bufferSize = 1024

msgFromServer = "Hello UDP Client"

bytesToSend = str.encode(msgFromServer)

# Creamos el datagram socket

UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# Conectamos la direccion ip con el puerto

UDPServerSocket.bind((localIP, localPort))

print("UDP server up and listening")

# Escuchamos los datagramas que vengan de los clientes

while (True):
    bytesAddressPair = UDPServerSocket.recvfrom(bufferSize)

    message = bytesAddressPair[0]

    address = bytesAddressPair[1]
    #Mensajes de los clientes
    clientMsg = "Message from Client:{}".format(message)
    clientIP = "Client IP Address:{}".format(address)

    print(clientMsg)
    print(clientIP)

    # Mandamos un reply al cliente 

    UDPServerSocket.sendto(bytesToSend, address)