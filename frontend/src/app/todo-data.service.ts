import {Injectable} from '@angular/core';
import {ToDo} from './todo';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class TodoDataService {

  observableItems: Observable<ToDo[]>;
  errorMessage: string;

  // Placeholder for todo's
  todos: ToDo[] = [];
   lastId: number = 0;

  url = "http://localhost:8080/todo/";
  constructor(private http:Http) {
    http.get(this.url+'/list').map(res => res.json()).subscribe(todos => 
    {
      this.todos = todos;
      var maxId = 0;
      this.todos.forEach(function (item:ToDo) {
        maxId = item.id>maxId?item.id:maxId;
      });
      this.lastId=maxId;
      });
  }

  // Simulate POST /todos
  addTodo(todo: ToDo): TodoDataService {
    todo.id = ++this.lastId;
    this.todos.push(todo);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    this.http.post(this.url+'/add', todo, options).subscribe(res => res.json);
    return this;
  }

  // Simulate DELETE /todos/:id
  deleteTodoById(id: number): TodoDataService {
    this.todos = this.todos
      .filter(todo => todo.id !== id);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let todo:ToDo = this.getTodoById(id);
    this.http.post(this.url+'/delete',todo, options).subscribe(res => res.json);
    return this;
  }

  // Simulate PUT /todos/:id
  updateTodoById(id: number, values: Object = {}): ToDo {
    let todo = this.getTodoById(id);
    if (!todo) {
      return null;
    }
    Object.assign(todo, values);
    return todo;
  }

  // Simulate GET /todos
  getAllTodos(showCompleted, showPending): ToDo[] {
    let flag: boolean = showCompleted && !showPending;
    return this.todos.filter(todo=>todo.complete===showCompleted || todo.complete!==showPending);
  }

  // Simulate GET /todos/:id
  getTodoById(id: number): ToDo {
    return this.todos
      .filter(todo => todo.id === id)
      .pop();
  }

  // Toggle todo complete
  toggleTodoComplete(todo: ToDo){
    let updatedTodo = this.updateTodoById(todo.id, {
      complete: !todo.complete
    });
    return updatedTodo;
  }

}