export class UpdateProject{
    id;
    unit;
    name;
    description;
    status;
    timeStart;
    timeEnd;
    userName;
    process;
    workOns: Work_On[];
}

export interface Projects{
  id;
  unit;
  name;
  description;
  status;
  timeStart;
  timeEnd;
  userName;
  process;
  workOns: Work_On[];
}
export interface Work_On {
  id: number;
  done: boolean;
  createAt: any;
}
export enum Process {
  TODO, WORK_ON, DONE
}
