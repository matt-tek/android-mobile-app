import { Module } from '@nestjs/common';
import { TodoModuleService } from './todo-module.service';
import { TodoModuleController } from './todo-module.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { TodoEntity } from './entities/todo-module.entity';

@Module({
  imports: [TypeOrmModule.forFeature([TodoEntity])],
  controllers: [TodoModuleController],
  providers: [TodoModuleService],
})
export class TodoModuleModule {}
