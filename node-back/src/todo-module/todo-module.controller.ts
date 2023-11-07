import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { TodoModuleService } from './todo-module.service';
import { CreateTodoDto } from './dto/create-todo-module.dto';
import { UpdateTodoModuleDto } from './dto/update-todo-module.dto';

@Controller('todo-module')
export class TodoModuleController {
  constructor(private readonly todoModuleService: TodoModuleService) {}

  @Post()
  create(@Body() createTodoModuleDto: CreateTodoDto) {
    return this.todoModuleService.create(createTodoModuleDto);
  }

  @Get()
  findAll() {
    return this.todoModuleService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.todoModuleService.findOne(+id);
  }

  @Patch(':id')
  update(
    @Param('id') id: string,
    @Body() updateTodoModuleDto: UpdateTodoModuleDto,
  ) {
    return this.todoModuleService.update(+id, updateTodoModuleDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.todoModuleService.remove(+id);
  }
}
