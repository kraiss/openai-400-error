import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.files.FileCreateParams
import com.openai.models.files.FileObject
import com.openai.models.files.FilePurpose

@main def mainko(): Unit = {
  val client = OpenAIOkHttpClient.builder().apiKey(OpenAIApiKey.apikey).build()
  val batchContent =
    "{\"custom_id\": \"request-1\", \"method\": \"POST\", \"url\": \"/v1/chat/completions\", \"body\": {\"model\": \"gpt-3.5-turbo-0125\", \"messages\": [{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},{\"role\": \"user\", \"content\": \"Hello world!\"}],\"max_tokens\": 1000}}"
  val fileCreateParams = FileCreateParams
    .builder()
    .file(batchContent.getBytes())
    .purpose(FilePurpose.BATCH)
    .build()
  val fileObject: FileObject = client.files().create(fileCreateParams)

  println(fileObject)
}