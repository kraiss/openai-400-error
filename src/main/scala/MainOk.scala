import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.files.FileCreateParams
import com.openai.models.files.FileObject
import com.openai.models.files.FilePurpose

import java.io.File
import java.io.PrintWriter
import scala.util.Using

@main def mainok(): Unit = {
  val client = OpenAIOkHttpClient.builder().apiKey(Constants.apikey).build()
  val batchContent = Constants.batchContent

  val jsonlFile = File.createTempFile("chat_completions", ".jsonl")
  Using.resource(new PrintWriter(jsonlFile)) { writer =>
    writer.println(batchContent)
  }

  val fileCreateParams = FileCreateParams
    .builder()
    .file(jsonlFile.toPath)
    .purpose(FilePurpose.BATCH)
    .build()
  val fileObject: FileObject = client.files().create(fileCreateParams)

  println(fileObject)
}