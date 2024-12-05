package restful.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restful.bean.Result;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Objects;

@Path("/file")
public class FileAPI {

    private static final String UPLOAD_DIR = "upload";

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces("application/json;charset=UTF-8")
    public Result uploadFile(
            @QueryParam("fileName") String fileName,
            InputStream uploadedInputStream,
            @Context HttpServletRequest request) {
        System.out.println(UPLOAD_DIR);
        if (fileName == null || fileName.isEmpty() || uploadedInputStream == null)
            return new Result(-3, "参数错误", null, "");

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(uploadedInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            // 计算 MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            String md5Hex = bytesToHex(md.digest());

            // 确保上传目录存在
            String realPath = request.getServletContext().getRealPath("/");
            System.out.println(realPath);
            java.nio.file.Path directory = Paths.get(realPath, UPLOAD_DIR);
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            // 保存文件并保留原始扩展名
            String fileExtension = getFileExtension(fileName);
            java.nio.file.Path filePath = directory.resolve(md5Hex + fileExtension);
            try (OutputStream out = Files.newOutputStream(filePath)) {
                byteArrayOutputStream.writeTo(out);
            }

            // 返回文件 URL
            String fileUrl = "/api/file/display/" + md5Hex + fileExtension;
            return new Result(0, "上传成功", fileUrl, "");

        } catch (Exception e) {
            return new Result(-100, "上传失败" + e.getMessage(), null, "");
        }
    }

    @GET
    @Path("/display/{fileName}")
    @Produces(MediaType.WILDCARD)
    public Response displayFile(@PathParam("fileName") String fileName, @Context HttpServletRequest request) {
        try {
            java.nio.file.Path filePath;
            String realPath = request.getServletContext().getRealPath("/");
            if (fileName.startsWith("default"))
                filePath = Paths.get(Objects.requireNonNull(getClass().getResource("/default/" + fileName)).toURI());
            else
                filePath = Paths.get(realPath + UPLOAD_DIR, fileName);
            if (Files.notExists(filePath)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            // 确定文件的 Content-Type
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // 默认类型
            }

            InputStream fileStream = Files.newInputStream(filePath);
            return Response.ok(fileStream, contentType).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("文件获取失败").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces("application/json;charset=UTF-8")
    public Result deleteFile(@QueryParam("fileName") String fileName) {
        fileName = fileName.split("/")[fileName.split("/").length - 1];
        try {
            java.nio.file.Path filePath = Paths.get(UPLOAD_DIR, fileName);
            if (Files.notExists(filePath)) {
                return new Result(-1, "文件不存在", null, "");
            }

            Files.delete(filePath);
            return new Result(0, "删除成功", null, "");

        } catch (Exception e) {
            return new Result(-100, "删除失败" + e.getMessage(), null, "");
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return (lastIndex != -1) ? fileName.substring(lastIndex) : "";
    }
}
