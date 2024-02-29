public class Pool2 extends Pool{ 
    int kidS = 0;
    int monit = 0;
    int ki;
    int cap;
    public synchronized void init(int ki, int cap){
        this.ki = ki;
        this.cap = cap;
    }

    public synchronized void kidSwims() throws InterruptedException{
        while(monit<1 || kidS>=monit*ki){
            log.waitingToSwim();
            wait();
        }
        kidS++;
        log.swimming();
        notifyAll();
    }
    public synchronized void kidRests(){
        log.resting(); 
        kidS--;
        notifyAll();
    }
    public synchronized void instructorSwims(){
        log.swimming();
        monit++;
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException{
        while(kidS>0 && monit==1 || (((monit-1)*ki)<=kidS && kidS > 0)){
            log.waitingToRest();
            wait();
        }
        monit--;
        log.resting();
        notifyAll();    
    }
}
