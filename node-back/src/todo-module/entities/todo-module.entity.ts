import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class TodoEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ nullable: false })
  task: string;

  @Column()
  isDone: boolean;

  @Column('datetime')
  deadline: string;
}
