import { PartialType } from '@nestjs/mapped-types';
import { CreateTodoDto } from './create-todo-module.dto';

export class UpdateTodoModuleDto extends PartialType(CreateTodoDto) {}
