import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.files.FileCreateParams
import com.openai.models.files.FileObject
import com.openai.models.files.FilePurpose

import java.io.ByteArrayInputStream

@main def mainko(): Unit = {
  val client = OpenAIOkHttpClient.builder().apiKey(Constants.apikey).build()
  val batchContent = Constants.batchContent

  val fileCreateParams = FileCreateParams
    .builder()
    .file(new ByteArrayInputStream(batchContent.getBytes))
    .purpose(FilePurpose.BATCH)
    .build()
  val fileObject: FileObject = client.files().create(fileCreateParams)

  println(fileObject)
}