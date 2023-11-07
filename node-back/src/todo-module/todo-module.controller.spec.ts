import { Test, TestingModule } from '@nestjs/testing';
import { TodoModuleController } from './todo-module.controller';
import { TodoModuleService } from './todo-module.service';

describe('TodoModuleController', () => {
  let controller: TodoModuleController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [TodoModuleController],
      providers: [TodoModuleService],
    }).compile();

    controller = module.get<TodoModuleController>(TodoModuleController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
