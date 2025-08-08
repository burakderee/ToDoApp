package com.example.todoapp.data.remote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.example.todoapp.data.remote.TaskDto

interface TaskApiService {
    @GET("todos")
    suspend fun getAllTodos(): List<TaskDto>

    @POST("todos")
    suspend fun addTodo(@Body task: TaskDto)

    @PUT("todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int,
        @Body todo: TaskDto
    )
}