
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合模式
 * 
 * ## 定义
 * 将一组对象组织成树形结构，以表示“部分-整体”的层次结构。
 * 组合模式让调用者可以统一单个对象和组合对象的处理逻辑。
 * 
 * ## 应用场景
 * 1. 主要用以处理树形数据
 * 2. 希望客户端以相同方式处理简单和复杂元素
 */
public class Composite {
    public static void main(String[] args) {

    }

    public static abstract class HumanResource {
        protected long id;
        protected double salary;

        public HumanResource(long id) {
            this.id = id;
        }

        public long getId() {
            return this.id;
        }

        public abstract double calculateSalary();
    }

    private static class Employee extends HumanResource {
        public Employee(long id, double salary) {
            super(id);
            this.salary = salary;
        }

        @Override
        public double calculateSalary() {
            return salary;
        }
    }

    private static class Department extends HumanResource {
        private List<HumanResource> subNodes = new ArrayList<>();

        public Department(long id) {
            super(id);
        }

        @Override
        public double calculateSalary() {
            double totalSalary = 0;
            for (HumanResource hr : subNodes) {
                totalSalary += hr.calculateSalary();
            }
            this.salary = totalSalary;
            return totalSalary;
        }

        public void addSubNode(HumanResource hr) {
            subNodes.add(hr);
        }
    }

    private static class DepartmentRepo {
        List<Long> getSubDepartmentIds(long departmentId) {
            return Arrays.asList(1l, 2l, 3l, 4l, 5l);
        }
    }

    private static class EmployeeRepo {
        List<Long> getDepartmentEmployeeIds(long departmentId) {
            return Arrays.asList(11l, 12l, 13l, 14l, 15l);
        }

        double getEmployeeSalary(long employeeId) {
            return employeeId * 10;
        }
    }

    private static class Demo {
        private static final long ORGANIZATION_ROOT_ID = 1001;
        private DepartmentRepo departmentRepo = new DepartmentRepo();
        private EmployeeRepo employeeRepo = new EmployeeRepo();

        public void buildOrganization() {
            Department rootDepartment = new Department(ORGANIZATION_ROOT_ID);
            buildOrganization(rootDepartment);
        }

        private void buildOrganization(Department department) {
            List<Long> subDepartmentIds = departmentRepo.getSubDepartmentIds(department.getId());
            for (Long subDepartmentId : subDepartmentIds) {
                Department subDepartment = new Department(subDepartmentId);
                department.addSubNode(subDepartment);
                buildOrganization(subDepartment);
            }
            List<Long> employeeIds = employeeRepo.getDepartmentEmployeeIds(department.getId());
            for (Long employeeId : employeeIds) {
                double salary = employeeRepo.getEmployeeSalary(employeeId);
                department.addSubNode(new Employee(employeeId, salary));
            }
        }
    }

}
