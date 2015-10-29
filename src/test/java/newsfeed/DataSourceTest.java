package newsfeed;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class DataSourceTest {

    DataSource source;
    DataSource.Observer observer;

    @Before
    public void setUpDataSource() {
        observer = mock(DataSource.Observer.class);
        source   = new DataSource();
        source.setObserver(observer);
    }

    @Test
    public void addingToDataSourceNotifiesChanges() {
        source.addContent(new Content());

        verify(observer).notifyNewContent(any(Content.class));
    }
}
