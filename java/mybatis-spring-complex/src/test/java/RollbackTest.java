import static org.junit.Assert.*;

import mapper.RemoteMapper;
import mapper.LocalMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.LocalRemoteService;
import entity.Local;
import entity.Remote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-context.xml")
public class RollbackTest {

    @Autowired
    private LocalRemoteService localRemoteService;
    @Autowired
    private LocalMapper localMapper;
    @Autowired
    private RemoteMapper remoteMapper;

    @Before
    public void setUp() throws Exception {
        localMapper.dropTable();
        localMapper.createTable();
        remoteMapper.dropTable();
        remoteMapper.createTable();
    }

    @Test
    public void testRollbackForMultiTable() throws Exception {
        // setup:
        Local local = new Local();
        local.setName("local");

        Remote remote = new Remote();
        remote.setName("remote");

        // when:
        // then:
        assertEquals(0, localRemoteService.findLocal().size());
        assertEquals(0, localRemoteService.findRemote().size());

        // when: Exception
        try {
            localRemoteService.save(local, remote, new Exception());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then: commit
        assertEquals(1, localRemoteService.findLocal().size());
        assertEquals(1, localRemoteService.findRemote().size());

        // when: RuntimeException
        try {
            localRemoteService.save(local, remote, new RuntimeException());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then: rollback and commit
        assertEquals(1, localRemoteService.findLocal().size());  // rollback
        assertEquals(2, localRemoteService.findRemote().size()); // commit
    }

}
