import socket


HOST = '127.0.0.1'  # The server's hostname or IP address
PORT = 5000        # The port used by the server

print("Hello, this is your Calculator console!")

isWorking = True
while isWorking:
    print("This is the menu :\n 1) Addition two number\n 2) Subtract two numbers\n 3) Multiply two numbers\n 4) Divide two numbers\n 5) exit ")
    operation = input("INFO => ::Write the number of the math op::")
    if int(operation) > 0 and int(operation) < 5:
        numbers = input("INFO => ::Write two numbers separated by (;)::")
        if ";" in numbers :
            s = socket.socket()
            s.connect((HOST, PORT))
            try:
                    components = operation + ":" + numbers
                    s.send(components.encode('utf-8'))
                    data = s.recv(1024)
                    print(":::::::::")
                    print(":::::::::")
                    print('Answer => ', data)
                    print(":::::::::")
                    print(":::::::::")
                    s.shutdown(socket.SHUT_WR)
                    s.close()
            finally:
                    print("Closing socket")
                   
        else:
     
                print("invalid format")   
    elif operation == "5":
        isWorking = False
    else:
        print("INFO => :: That number is incorrect ::")
            
print("Calculator off")
    
