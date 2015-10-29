package newsfeed;

import rx.Observable;
import rx.subjects.ReplaySubject;


public class Newsfeed implements DataSource.Observer {
    ReplaySubject<Content> contents = ReplaySubject.create();

    public void addSource(DataSource source) {
        source.setObserver(this);
    }

    @Override
    public void notifyNewContent(Content content) {
        contents.onNext(content);
    }

    public Observable<Content> fetchAsync() {
        return contents;
    }
}
