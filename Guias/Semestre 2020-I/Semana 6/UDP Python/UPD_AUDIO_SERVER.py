import socket
import Pyaudio
from threading import Thread

frames = []

def udpStream():
    udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    while True:
        if len(frames) > 0:
            udp.sendto(frames.pop(0), ("127.0.0.1", 12345)) #
    udp.close()

def record(stream, CHUNK):
    while True:
        frames.append(stream.read(CHUNK))

if __name__ == "__main__":
    CHUNK = 1024
    FORMAT = pyaudio.paInt16 #Decodificacion del audioo
    CHANNELS = 2 #Escoger si es sterio o mono
    RATE = 44100 #El rate del audio

    Audio = pyaudio.PyAudio()

    stream = Audio.open(format = FORMAT,
                    channels = CHANNELS,
                    rate = RATE,
                    input = True,
                    frames_per_buffer = CHUNK,
                    )

#Inicializacion de hilos
    AudioThread = Thread(target = record, args = (stream, CHUNK,))
    udpThread = Thread(target = udpStream)
    AudioThread.setDaemon(True)
    udpThread.setDaemon(True)
    AudioThread.start()
    udpThread.start()
    AudioThread.join()
    udpThread.join()
