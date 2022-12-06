**Hướng dẫn chạy và test ứng dụng**
- truy cập http://localhost:8080/ để vào trang login vào ứng dụng

- UserId và Password được show trong đường dẫn http://localhost:8080/portal/index-users (lưu trong MongoDB) hoặc bấm vào link "Click here", sau khi đổi mật khẩu có thể truy cập vào trang này để kiểm tra lại. Trang này chỉ dùng để phục vụ mục đích kiểm tra dữ liệu nên có thể truy cập bất cứ khi nào ứng dụng được chạy

- Các chức năng của ứng dụng được implemented như yêu cầu của đề bài.

- Nếu đăng nhập đúng ở lần đầu tiên, User sẽ được dẫn đến trang đổi mật khẩu cũng như tạo Hint Question (chỉ có UI, không lưu được Hint question), chỉ có thể thay đổi được mật khẩu nếu như nhập đúng mật khẩu cũ, và mật khẩu mới đúng định dạng và khớp nhau. Nếu sai sẽ đưa User đến trang khóa UserId tạm thời.

- Nếu đăng nhập đúng ở lần thứ 2 trở đi, đưa User đến trang Home để sử dụng các tính năng của MyGSet (placeholder page)

- Sau khi đăng nhập với 3 lần sai mật khẩu, userId này sẽ bị khóa (nếu UserId có tồn tại), lúc đó chỉ cần bấm vào "HERE" ở trang call center, UserId đó sẽ được mở khóa. Kiểm tra lại mật khẩu ở trang http://localhost:8080/portal/index-users để đăng nhập lại

**Về ứng dụng**

- Ứng dụng được build bằng Java Servlet và JSP, sử dụng MongoDB làm hệ cở sở dữ liệu. Có thể kiểm tra database của ứng dụng qua URI: **mongodb+srv://xhieu2206:funix123@funix-assignment.f61wol3.mongodb.net/?retryWrites=true&w=majority**
- Để chạy ứng dụng, cần cài đặt Tomcat phiên bản 9 trở lên, Java 11 là tối thiểu.
- Unit test được viết trong forder **test**. Toàn bộ unit test được export ra file _**Test Results - com_hieunguyen_in_servlet_assignment.html**_ được lưu trong thư mục gốc của project
- Source code for project: https://github.com/xhieu2206/lab_3_assignment.git
