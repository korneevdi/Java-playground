package airport.service;

import airport.dao.StatusDao;

import java.util.List;

public class StatusService {

    private final StatusDao statusDao;

    public StatusService(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    public void showAllStatuses() {
        List<String> allStatuses = statusDao.findAll();
        System.out.println("List of statuses:");
        if(allStatuses != null && allStatuses.size() != 0) {
            for(int i = 0; i < allStatuses.size(); i++) {
                System.out.println((i + 1) + ": " + allStatuses.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findStatus(int id) {
        String status = statusDao.findById(id);
        if(!status.isEmpty()) {
            System.out.println("Status: " + status);
        } else {
            System.out.println("No data found");
        }
    }

    public void addStatus(String status) {
        if(status != null && !status.isEmpty()) {
            statusDao.insert(status);
            System.out.println("New element '" + status + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateStatus(String oldStatus, String newStatus) {
        if(oldStatus != null && newStatus != null && !oldStatus.isEmpty() && !newStatus.isEmpty()) {
            int id = statusDao.findId(oldStatus);
            if(id != -1) {
                statusDao.update(id, newStatus);
                System.out.println("Status updated successfully");
            } else{
                System.out.println("Element '" + oldStatus + "' not found");
            }
        } else {
            System.out.println("'oldStatus' and 'newStatus' should not be NULL or empty");
        }
    }

    public void deleteStatus(String status) {
        if(status != null && !status.isEmpty()) {
            if(statusDao.delete(status)) {
                System.out.println("Status '" + status + "' deleted successfully");
            } else {
                System.out.println("Element '" + status + "' not found");
            }
        } else{
            System.out.println("Status should not be NULL or empty");
        }
    }
}
