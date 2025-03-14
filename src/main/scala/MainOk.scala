import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.ChatModel
import com.openai.models.chat.completions.ChatCompletionCreateParams
import com.openai.models.files.FileCreateParams
import com.openai.models.files.FileObject
import com.openai.models.files.FilePurpose

import java.io.File
import java.io.PrintWriter
import scala.util.Using

@main def mainok(): Unit = {
  val client = OpenAIOkHttpClient.builder().apiKey(OpenAIApiKey.apikey).build()
  val batchContent = """{"custom_id": "request-1", "method": "POST", "url": "/v1/chat/completions", "body": {"model": "gpt-3.5-turbo-0125", "messages": [{"role": "system", "content": "You are a helpful assistant."},{"role": "user", "content": "Hello world!"}],"max_tokens": 1000}}"""

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