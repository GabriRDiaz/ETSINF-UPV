public class Pool3 extends Pool{
    int kidS = 0;
    int monit = 0;
    int ki;
    int cap;
    public synchronized void init(int ki, int cap){
        this.ki = ki;
        this.cap = cap;
    }

    public synchronized void kidSwims() throws InterruptedException{
        while(monit<1 || kidS>=monit*ki || cap==0){
            log.waitingToSwim();
            wait();
        }
        kidS++;
        cap--;
        log.swimming();
        notifyAll();
    }
    public synchronized void kidRests(){
        log.resting(); 
        kidS--;
        cap++;
        notifyAll();
    }
    public synchronized void instructorSwims() throws InterruptedException{
        while(cap==0){
            log.waitingToSwim();
            wait();
        }
        log.swimming();
        monit++;
        cap--;
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException{
        while(kidS>0 && monit==1 || (((monit-1)*ki)<=kidS && kidS > 0)){
            log.waitingToRest();
            wait();
        }
        monit--;
        cap++;
        log.resting();
        notifyAll();    
    }
}
