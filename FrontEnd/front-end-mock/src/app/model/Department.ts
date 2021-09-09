import { UpdateProject } from "./UpdateProject";
import { updateUser } from "./updateUser";

export class Department {
  id;
  code: string;
  location: string;
  description: string;
  createAt: any;
  users: updateUser[];
  projects: UpdateProject[];
}
