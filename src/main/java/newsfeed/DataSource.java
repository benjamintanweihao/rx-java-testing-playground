package newsfeed;

public class DataSource {

    Observer mObserver;

    public void addContent(Content content) {
        if (mObserver != null) mObserver.notifyNewContent(content);
    }

    public void setObserver(Observer observer) {
        mObserver = observer;
    }

    public interface Observer {
        void notifyNewContent(Content content);
    }

}
