package se.miun.antonsskafferi.resource;

//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;

@Path("/api")
public class FileResource {

    @GET
    @Path("/file")
    public String test() {
        return "test";
    }

//    @POST
//    @Path("/file/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition fileDetail) {
//
//        String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();
//
//        // save it
//        writeToFile(uploadedInputStream, uploadedFileLocation);
//
//        String output = "File uploaded to : " + uploadedFileLocation;
//
//        return Response.status(200).entity(output).build();
//
//    }
//
//    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
//        try {
//            OutputStream out = new FileOutputStream(new File(
//                    uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
}
