package newsfeed;

import org.junit.Before;
import org.junit.Test;
import rx.observers.TestSubscriber;

public class NewsfeedTest {

    DataSource source;
    DataSource anotherSource;
    Newsfeed newsfeed;

    @Before
    public void setUpNewsfeed() {
        source = new DataSource();
        anotherSource = new DataSource();

        newsfeed = new Newsfeed();
        newsfeed.addSource(source);
        newsfeed.addSource(anotherSource);
    }

    @Test
    public void newsfeedIsSubscribable() {
        TestSubscriber<Content> testSubscriber = new TestSubscriber<>();

        Content first  = new Content();
        Content second = new Content();
        source.addContent(first);
        source.addContent(second);

        newsfeed.fetchAsync().subscribe(testSubscriber);

        testSubscriber.assertValues(first, second);
    }

    @Test
    public void newsfeedFetchesFromMultipleDataSources() {
        TestSubscriber<Content> testSubscriber = new TestSubscriber<>();

        Content first  = new Content();
        Content second = new Content();
        source.addContent(first);
        anotherSource.addContent(second);

        newsfeed.fetchAsync().subscribe(testSubscriber);

        testSubscriber.assertValues(first, second);
    }

    @Test
    public void newsfeedFetchesWithMultipleSubscribers() {
        TestSubscriber<Content> testSubscriber1 = new TestSubscriber<>();
        TestSubscriber<Content> testSubscriber2 = new TestSubscriber<>();

        Content first  = new Content();
        Content second = new Content();

        source.addContent(first);
        anotherSource.addContent(second);

        newsfeed.fetchAsync().subscribe(testSubscriber1);
        newsfeed.fetchAsync().subscribe(testSubscriber2);

        testSubscriber2.assertValues(first, second);
    }
}
