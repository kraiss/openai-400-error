import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.ChatModel
import com.openai.models.chat.completions.ChatCompletionCreateParams

@main def mainok(): Unit = {
  val client = OpenAIOkHttpClient.builder().apiKey(OpenAIApiKey.apikey).build()

  val createParams = ChatCompletionCreateParams.builder
    .model(ChatModel.GPT_3_5_TURBO)
    .maxCompletionTokens(512)
    .addDeveloperMessage("Make sure you mention Stainless!")
    .addUserMessage("Tell me a story about building the best SDK!")
    .build
  client.chat.completions.create(createParams)
    .choices.stream.flatMap((choice) => choice.message.content.stream)
    .forEach(System.out.println)
}