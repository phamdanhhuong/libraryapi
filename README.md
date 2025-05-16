# 📚 Library API - Spring Boot

Đây là một ứng dụng API quản lý thư viện sử dụng Spring Boot và MySQL.

## 🛠️ Hướng dẫn cài đặt & chạy project

### Bước 1: Clone project về máy

```bash
git clone https://github.com/phamdanhhuong/libraryapi.git
cd libraryapi
```
### Bước 2: Tạo và khởi tạo cơ sở dữ liệu MySQL
Đảm bảo bạn đã cài đặt MySQL và đang chạy.

Mở file dbLibCreate.sql trong thư mục gốc của project và chạy nội dung SQL đó trong MySQL để tạo database và các bảng cần thiết.

### Bước 3: Cấu hình email gửi mail (SMTP)
Trong thư mục src/main/resources, tạo file mới tên là application-secrets.properties và thêm nội dung sau vào:

```bash
spring.mail.host=smtp.gmail.com
spring.mail.username=example@gmail.com       # ← Thay bằng email thật
spring.mail.password=your_app_password       # ← Thay bằng mật khẩu ứng dụng (App Password)
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.smtp.ssl.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.smtp.debug=true
```
Mở file src/main/resources/application.properties và sửa lại các thông số kết nối cơ sở dữ liệu theo thông tin của bạn. Ví dụ, nếu bạn sử dụng thông số dưới đây:
```bash
spring.datasource.username=library
spring.datasource.password=1234
```

### Bước 4: Chạy ứng dụng
Sử dụng IDE (IntelliJ, STS, VS Code...)
Mở project với IDE bạn chọn.

Chạy file LibraryapiApplication.java trong src/main/java/com/library/libraryapi/LibraryapiApplication.java.
