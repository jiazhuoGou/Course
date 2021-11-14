package course;

public class StudentServiceProxy implements IStudentService{
    private IStudentService studentService;

    public StudentServiceProxy(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void insertStudent() {
        System.out.println("准备添加学生");
        studentService.insertStudent();
        System.out.println("添加学生成功");
    }

    @Override
    public void deleteStudent() {
        System.out.println("准备删除学生");
        studentService.deleteStudent();
        System.out.println("删除学生成功");
    }
}
