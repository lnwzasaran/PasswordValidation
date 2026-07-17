// ดูโจทย์ วิธีทำใน README.md
// หน้าที่ของคุณ: ออกแบบ test เอง แล้วเติม check(...) ให้ครบทุก branch
public class TestRunner {

    static int pass = 0, fail = 0;

    static void check(String name, boolean ok) {
        if (ok) { pass++; System.out.println("  [PASS] " + name); }
        else    { fail++; System.out.println("  [FAIL] " + name); }
    }

    public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("** คำเตือน: assertion ปิดอยู่ รันด้วย  java -ea TestRunner **");

        System.out.println("== Password Validation ==");

        // ตัวอย่าง assertion ปกติ (ตัวแทนกลุ่ม valid)
        check("'Abcdef12' valid", PasswordValidator.validate("Abcdef12") == true);

        // ตัวอย่างแพตเทิร์นทดสอบ "ต้อง throw" ด้วย try/catch
        boolean threw = false;
        try { PasswordValidator.validate(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("null -> throws IllegalArgumentException", threw == true);

        // TODO: R2 - boundary ความยาว (เช่น 7, 8, 20, 21)
        check("pw len = 8" , PasswordValidator.validate("aA345678") == true);
        check("pw len < 8" , PasswordValidator.validate("aA123") == false);
        check("pw len = 20" , PasswordValidator.validate("aaaaaaaaaaaaaaA56789") == true);
        check("pw len > 20" , PasswordValidator.validate("aaaaaaaaaaaaaaA567890") == false);
        // TODO: R3 - ไม่มีตัวพิมพ์ใหญ่ -> false
        check("pw no upper" , PasswordValidator.validate("aaaa56789") == false);
        //check("pw have upper" , PasswordValidator.validate("aaAa56789") == true);
        // TODO: R4 - ไม่มีตัวพิมพ์เล็ก -> false
        check("pw no lower" , PasswordValidator.validate("AAAA56789") == false);
        //check("pw have lower" , PasswordValidator.validate("aAAA56789") == true);
        // TODO: R5 - ไม่มีตัวเลข -> false
        check("pw no digit" , PasswordValidator.validate("aAAAAAAA") == false);
        //check("pw have number" , PasswordValidator.validate("aAAA56789") == true);
        // TODO: R6 - มีช่องว่าง -> false
        check("pw have space" , PasswordValidator.validate("aAAA67 1") == false);
        // TODO: boundary อื่นๆ ที่คุณคิดว่าจำเป็น
        check("pw have other" , PasswordValidator.validate("") == false);
        check("pw have other" , PasswordValidator.validate("aA1!@#$%") == true);
        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
        System.exit(fail == 0 ? 0 : 1);
    }
}
