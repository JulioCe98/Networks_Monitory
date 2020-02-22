import socket

HOST = "127.0.0.1"
PORT = 5000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    print("Server is waiting for a client...")
    s.bind((HOST, PORT))
    s.listen()
    while True:
        conn, addr = s.accept()
        try:
            print('Connected by', addr)
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                else:
                    operation = ""
                    components = data.decode('utf-8')
                    components = components.split(":")
                    if len(components) == 2: 
                        factors = components[1].split(";")
                        if factors[0] != "" and factors[1] != "":
                            factors[0] = int(factors[0])
                            factors[1] = int(factors[1])
                            if components[0] == "1":
                                    operation = str(factors[0] + factors[1])
                            elif components[0] == "2":   
                                    operation = str(factors[0] - factors[1])
                            elif components[0] == "3":   
                                    operation = str(factors[0] * factors[1])
                            else:
                                if(factors[1] != 0):
                                        operation = str(factors[0] / factors[1])
                                else:
                                    operation = "Invalid numbers (div/0)"
                        else:
                                  operation = "Invalid format, one of the numbers is missing"
                    else:
                            operation = "Invalid format, one of the components is missing"
                            
                    conn.send(str(operation).encode('utf-8'))
        finally:
            conn.close()
            print("Connection was closed")
