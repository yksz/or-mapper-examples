import java.util.List;

import entity.Local;
import entity.Remote;
import service.LocalService;
import service.RemoteService;
import service.ServiceFactory;

public class Sample {

    public static void main(String[] args) throws Exception {
        Local local = new Local();
        local.setName("local");

        LocalService localService = ServiceFactory.getLocalService();
        localService.save(local);
        show(localService.findAll(), "findAll:");
        show(localService.findByName("local"), "findByName:");


        Remote remote = new Remote();
        remote.setName("remote");

        RemoteService remoteService = ServiceFactory.getRemoteService();
        remoteService.save(remote);
        show(remoteService.findAll(), "findAll:");
        show(remoteService.findByName("remote"), "findByName:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
