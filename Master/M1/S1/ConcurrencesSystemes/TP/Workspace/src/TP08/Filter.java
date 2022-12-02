package TP08;
public class Filter {
    private Thread sender;
    public Filter() {
        this.sender = null;
    }

    public Filter(Thread sender_) {
        this.sender = sender_;
    }

    public Thread getSender() {
        return(this.sender);
    }

    public boolean isValid(Message msg_) {
        boolean result_ = true;
        if(this.sender != msg_.getSender()) {
            result_ = false;
        }
        return(result_);
    }
}
