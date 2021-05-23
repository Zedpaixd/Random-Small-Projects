from playsound import playsound
import time

a = int ( input ( "Time?" ) ) 
while True:
    t = a
    while t > 0:
        t-=1
        time.sleep(1)
    playsound("dontopen.mp3")