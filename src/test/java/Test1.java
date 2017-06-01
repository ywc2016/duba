import db.LinkDao;
import org.junit.Test;
import po.Link;

/**
 * Created by yuwc on 2017/6/1.
 */
public class Test1 {
    @Test
    public void TestLinkDao() {
        LinkDao linkDao = new LinkDao();
        Link link = new Link();
        link.setTitle("test");
        linkDao.save(link);
    }
}
