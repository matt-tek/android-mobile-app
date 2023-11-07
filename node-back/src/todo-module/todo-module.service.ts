import { Injectable } from '@nestjs/common';
import { CreateTodoDto } from './dto/create-todo-module.dto';
import { UpdateTodoModuleDto } from './dto/update-todo-module.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { TodoEntity } from './entities/todo-module.entity';
import { Repository } from 'typeorm';

@Injectable()
export class TodoModuleService {
  constructor(
    @InjectRepository(TodoEntity)
    private todoRepository: Repository<TodoEntity>,
  ) {}

  create(createTodoDto: CreateTodoDto) {
    return this.todoRepository.save(createTodoDto);
  }

  findAll(): Promise<TodoEntity[]> {
    return this.todoRepository.find();
  }

  findOne(id: number) {
    return `This action returns a #${id} todoModule`;
  }

  update(id: number, updateTodoModuleDto: UpdateTodoModuleDto) {
    return `This action updates a #${id} todoModule`;
  }

  remove(id: number) {
    return `This action removes a #${id} todoModule`;
  }
}
